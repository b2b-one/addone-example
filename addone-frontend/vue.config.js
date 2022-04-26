process.env.NODE_ENV === 'production'
// eslint-disable-next-line @typescript-eslint/no-var-requires
const { WebpackWarPlugin } = require('webpack-war-plugin')

const webpackWarPluginOptions = {
    archiveName: 'addone-frontend',
    webInf: './WEB-INF',
    additionalElements: [{ path: './WEB-INF/web.xml', destPath: './WEB-INF/web.xml' }],
}

module.exports = {
    publicPath: process.env.NODE_ENV === 'production' ? '/addone-frontend/' : '/',
    configureWebpack: () => {
        if (process.env.NODE_ENV === 'production') {
            return {
                plugins: [new WebpackWarPlugin(webpackWarPluginOptions)],
            }
        }
    },
}
