<template>
  <div id="app">
    <div id="nav">
      <header-component>></header-component>
    </div>
    <router-view/>
  </div>
</template>

<script>
import HeaderComponent  from './components/HeaderComponent'

export default {
  name: 'App',
  components: {
    HeaderComponent
  }
}
</script>


<style>

</style>
