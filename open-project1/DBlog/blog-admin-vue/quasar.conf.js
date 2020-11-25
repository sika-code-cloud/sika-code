/*
 * This file runs in a Node context (it's NOT transpiled by Babel), so use only
 * the ES6 features that are supported by your Node version. https://node.green/
 */

// Configuration for your app
// https://quasar.dev/quasar-cli/quasar-conf-js
/* eslint-env node */
const path = require('path')

module.exports = function (ctx) {
  return {
    // https://quasar.dev/quasar-cli/supporting-ts
    supportTS: false,

    // https://quasar.dev/quasar-cli/prefetch-feature
    // preFetch: true,

    // app boot file (/src/boot)
    // --> boot files are part of "main.js"
    // https://quasar.dev/quasar-cli/boot-files
    boot: ['main.js'],

    // https://quasar.dev/quasar-cli/quasar-conf-js#Property%3A-css
    css: ['app.sass'],

    // https://github.com/quasarframework/quasar/tree/dev/extras
    extras: [
      // 'ionicons-v4',
      // 'mdi-v5',
      // 'fontawesome-v5',
      // 'eva-icons',
      'themify',
      // 'line-awesome',
      // 'roboto-font-latin-ext', // this or either 'roboto-font', NEVER both!

      'roboto-font', // optional, you are not bound to it
      'material-icons' // optional, you are not bound to it
    ],

    // Full list of options: https://quasar.dev/quasar-cli/quasar-conf-js#Property%3A-build
    build: {
      vueRouterMode: 'history', // available values: 'hash', 'history'
      // transpile: false,

      // Add dependencies for transpiling with Babel (Array of string/regex)
      // (from node_modules, which are by default not transpiled).
      // Applies only if "transpile" is set to true.
      // transpileDependencies: [],

      // rtl: false, // https://quasar.dev/options/rtl-support
      // preloadChunks: true,
      // showProgress: false,
      // gzip: true,
      // analyze: true,

      // Options below are automatically set depending on the env, set them if you want to override
      // extractCSS: false,
      env: ctx.dev
        ? {
            VUE_APP_BASE_API: 'http://dev.api.com/api'
          }
        : {
            VUE_APP_BASE_API: 'http://prod.api.com'
          },
      // https://quasar.dev/quasar-cli/handling-webpack
      extendWebpack(cfg, { isServer, isClient }) {
        cfg.resolve.alias = {
          ...cfg.resolve.alias, // This adds the existing alias

          // Add your own alias like this
          api: path.resolve(__dirname, './src/api'),
          statics: path.resolve(__dirname, './src/statics'),
          utils: path.resolve(__dirname, './src/utils'),
          mock: path.resolve(__dirname, './src/mock'),
          '@': path.resolve(__dirname, './src')
        }
        cfg.module.rules.push({
          enforce: 'pre',
          test: /\.(js|vue)$/,
          loader: 'eslint-loader',
          exclude: /node_modules/
        })
      }
    },

    // Full list of options: https://quasar.dev/quasar-cli/quasar-conf-js#Property%3A-devServer
    devServer: {
      https: false,
      port: 8080,
      open: true, // opens browser window automatically
      proxy: {
        // 将所有以/api开头的请求代理到jsonplaceholder
        '/api': {
          target: 'http://localhost:8085',
          changeOrigin: true,
          pathRewrite: {
            '^/api': ''
          }
        }
      }
    },
    // https://quasar.dev/quasar-cli/quasar-conf-js#Property%3A-framework
    framework: {
      iconSet: 'material-icons', // Quasar icons set
      lang: 'en-us', // Quasar language pack
      config: {
        brand: {
          // src/sass/quasar.variables.sass
          primary: '#1890ff',
          secondary: '#26A69A',
          accent: '#9C27B0',
          dark: '#1d1d1d',
          positive: '#21BA45',
          negative: '#ff4d4f',
          info: '#31CCEC',
          warning: '#F2C037'
        }
      },

      // Possible values for "importStrategy":
      // * 'auto' - (DEFAULT) Auto-import needed Quasar components & directives
      // * 'all'  - Manually specify what to import
      importStrategy: 'auto',

      // For special cases outside of where "auto" importStrategy can have an impact
      // (like functional components as one of the examples),
      // you can manually specify Quasar components/directives to be available everywhere:
      //
      // components: [],
      // directives: [],

      // Quasar plugins
      plugins: [
        'Loading',
        'Notify',
        'LoadingBar',
        'LocalStorage',
        'SessionStorage',
        'Meta'
      ]
    },

    // animations: 'all', // --- includes all animations
    // https://quasar.dev/options/animations
    animations: 'all',

    // https://quasar.dev/quasar-cli/developing-ssr/configuring-ssr
    ssr: {
      pwa: false
    },

    // https://quasar.dev/quasar-cli/developing-pwa/configuring-pwa
    pwa: {
      workboxPluginMode: 'GenerateSW', // 'GenerateSW' or 'InjectManifest'
      workboxOptions: {}, // only for GenerateSW
      manifest: {
        name: 'blog-admin-vue',
        short_name: 'blog-admin-vue',
        description: 'blog-admin-vue',
        display: 'standalone',
        orientation: 'portrait',
        background_color: '#ffffff',
        theme_color: '#027be3',
        icons: [
          {
            src: 'icons/icons-128x128.png',
            sizes: '128x128',
            type: 'image/png'
          },
          {
            src: 'icons/icons-192x192.png',
            sizes: '192x192',
            type: 'image/png'
          },
          {
            src: 'icons/icons-256x256.png',
            sizes: '256x256',
            type: 'image/png'
          },
          {
            src: 'icons/icons-384x384.png',
            sizes: '384x384',
            type: 'image/png'
          },
          {
            src: 'icons/icons-512x512.png',
            sizes: '512x512',
            type: 'image/png'
          }
        ]
      }
    },

    // Full list of options: https://quasar.dev/quasar-cli/developing-cordova-apps/configuring-cordova
    cordova: {
      // noIosLegacyBuildFlag: true, // uncomment only if you know what you are doing
    },

    // Full list of options: https://quasar.dev/quasar-cli/developing-capacitor-apps/configuring-capacitor
    capacitor: {
      hideSplashscreen: true
    },

    // Full list of options: https://quasar.dev/quasar-cli/developing-electron-apps/configuring-electron
    electron: {
      bundler: 'packager', // 'packager' or 'builder'

      packager: {
        // https://github.com/electron-userland/electron-packager/blob/master/docs/api.md#options
        // OS X / Mac App Store
        // appBundleId: '',
        // appCategoryType: '',
        // osxSign: '',
        // protocol: 'myapp://path',
        // Windows only
        // win32metadata: { ... }
      },

      builder: {
        // https://www.electron.build/configuration/configuration

        appId: 'blog-admin-vue'
      },

      // More info: https://quasar.dev/quasar-cli/developing-electron-apps/node-integration
      nodeIntegration: true,

      extendWebpack(cfg) {}
    }
  }
}
