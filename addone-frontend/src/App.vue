<template>
  <div class="content">
    <div>Hey, i'm <b>AddONe</b></div>

    <div v-if="isInitialized">
      <div><button @click="getToken">Authorize - get AuthorizeWithOne token</button></div>
      <div><button @click="getUserName">Validate token</button></div>

      <div v-if="userName">AuthorizeWithOne token valid, logged user: {{userName}}</div>
      <div v-else-if="authError">Unauthorized</div>
    </div>
  </div>
</template>


<script lang="ts">
import {useOneSdk} from "@one-commerce/sdk-backoffice-vue3";
import {computed, ref} from 'vue'
import axios from "axios";

export default {
  name: "ExamplePlugin",
  setup() {
    const $one = useOneSdk()
    const token = ref('')
    const userName = ref('')
    const authError = ref(false)

    const isInitialized = computed(() => $one?.isInitialized)

    function getToken() {
      $one!.authorization.getAuthToken().then((response)=> {
        token.value = response
        window.alert("Token received")
      }).catch(()=> {
        window.alert("Token retrieval error")
      })
    }

    function isTokenValid() {
      return axios.post(
          'https://apps.b2b.one/addone-backend/validateToken', // Plugin backend app url
          {
            token: token.value,
            oneBoUrl: window.location.ancestorOrigins[0], //ONe backoffice url
            tenantKey: $one?.tenant.tenantKey,
            pluginId: 'addoneExample'
          })
    }

    function getUserName() {
      isTokenValid().then(()=>{
        userName.value = $one!.user.name!
        authError.value = false
        window.alert("Token valid")
      }).catch(()=>{
        authError.value = true
        window.alert("Unauthorized")
      })
    }

    return {token, getToken, userName, getUserName, authError, isInitialized}
  }
}
</script>

<style lang="scss">
.content {
  margin-top: 100px;
  text-align: center;
  font-family: Avenir, Helvetica, Arial, sans-serif;
}
button {
  margin: 20px 0;
}
</style>
