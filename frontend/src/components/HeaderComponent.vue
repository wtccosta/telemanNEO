<template>
<div>
  <b-navbar toggleable="lg" type="dark" variant="dark">
     <router-link tag="b-navbar-brand" to="/" >TelemaNEO</router-link>
    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

    <b-collapse id="nav-collapse" is-nav>
      <b-navbar-nav>
         <!-- Navbar dropdowns -->
      <b-nav-item-dropdown text="Técnico" right>
       
        <router-link tag="b-dropdown-item" to="/tecnico/terminais" >Terminais</router-link>
    
      </b-nav-item-dropdown>

        <router-link tag="b-nav-item-dropdown :t" to="/tecnico" >Técnico</router-link>
        <router-link tag="b-nav-item" to="/financeiro" >Financeiro</router-link>
      </b-navbar-nav>

      <!-- Right aligned nav items -->
      <b-navbar-nav class="ml-auto">

        <b-nav-item-dropdown right>
          <!-- Using 'button-content' slot -->
          <template #button-content>
            <em>Bob Brow</em>
          </template>
          <b-dropdown-item href="#">Perfil</b-dropdown-item>
          <b-dropdown-item href="#">Sair</b-dropdown-item>
        </b-nav-item-dropdown>
      </b-navbar-nav>
    </b-collapse>
  </b-navbar>
</div>
</template>

<script>
export default {};
</script>

<style>
</style>