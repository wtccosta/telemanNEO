import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

import {state, mutations} from './default'

import products from './modules/products'

export default new Vuex.Store({
    modules: {
      products
    },
    state,
    mutations
  })
  