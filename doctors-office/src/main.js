import "bootstrap/dist/css/bootstrap.css"
import "bootstrap/dist/js/bootstrap.js"
import { createApp } from 'vue'
import { createStore } from 'vuex'
import App from './App.vue'
import router from './router'

const store = createStore({
    state () {
      return {
        appointment: ''
      }
    },
    mutations: {
      change (state, selectedApp) {
        state.appointment=selectedApp
      }
    }
  })

createApp(App).use(router).use(store).mount('#app')