import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

import {state, mutations} from './default'

export default new Vuex.Store({
    modules: {
    
    },
    state,
    mutations
  })
  