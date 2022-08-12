<template >
<div style="min-height:76.05vh">
  <!--<nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link>
  </nav>-->
  <nav class="navbar navbar-expand-lg bg-white navbar-light shadow-sm px-5 py-3 py-lg-0">
        <a href="/" class="navbar-brand p-0">
            <h1 class="m-0 text-primary"><i class="fa fa-heartbeat me-2"></i>Relax</h1>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <div class="navbar-nav ms-auto py-0">
                <a href="/" class="nav-item nav-link active">Naslovna</a>
                <a href="calendar" v-if="this.role==='ROLE_NURSE'" class="nav-item nav-link">Kalendar termina</a>
                <a href="about.html" v-if="!this.role" class="nav-item nav-link">Usluge</a>
                <a href="service.html" v-if="!this.role" class="nav-item nav-link">O nama</a>
                <div v-if="!this.role" class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Pages</a>
                    <div class="dropdown-menu m-0">
                        <a href="price.html" class="dropdown-item">Pricing Plan</a>
                        <a href="team.html" class="dropdown-item">Our Dentist</a>
                        <a href="testimonial.html" class="dropdown-item">Testimonial</a>
                        <a href="appointment.html" class="dropdown-item">Appointment</a>
                    </div>
                </div>
                <a href="contact.html" v-if="!this.role" class="nav-item nav-link">Kontakt</a>
            </div>
            <button type="button" v-if="!this.role" class="btn text-dark" data-bs-toggle="modal" data-bs-target="#searchModal"><i class="fa fa-search"></i></button>
            <a href="login" v-if="!this.role" class="btn btn-primary py-2 px-4 ms-3">Prijavite se</a>
            <a v-on:click="logout" v-if="this.role" class="btn btn-primary py-2 px-4 ms-3">Odjavite se</a>
        </div>
    </nav>

  <router-view/>

</div>
      <div class="container-fluid  animated animatedFadeInUp fadeInUp footer" style="padding:2%">
        <table style="width:100%">
          <td style="width:20%;height:1px">
            <div style="text-align:left;float:right;height:100%">
                    <h3 style=" margin-top:0%" class="text-white mb-4" >Kontaktirajte nas</h3>
                    <p class="mb-2"><i style="color: #0DE6FD;" class="bi bi-geo-alt me-2"></i>123 Glavna, Ruma, Srbija</p>
                    <p class="mb-2"><i style="color: #0DE6FD;" class="bi bi-envelope-open me-2"></i>vsrelax@example.com</p>
                    <p class="mb-0"><i style="color: #0DE6FD;" class="bi bi-telephone me-2"></i>+012 345 67890</p>
                </div>
          </td>
          <td style="width:20%;">
            </td>
          <td style="width:20%;height:1px">         
            <div style="text-align:center;padding-left:20%;height:100%">
                    <h3 style="text-align:left" class="text-white mb-4">Zapratite nas</h3>
                    <div class="d-flex" style="margin-top:12%">
                        <a  class="btn btn-lg btn-primary navbar-color btn-lg-square rounded me-2" href="#"><i  class="fab fa-twitter fw-normal"></i></a>
                        <a class="btn btn-lg btn-primary navbar-color btn-lg-square rounded me-2" href="#"><i class="fab fa-facebook-f fw-normal"></i></a>
                        <a class="btn btn-lg btn-primary navbar-color btn-lg-square rounded me-2" href="#"><i class="fab fa-linkedin-in fw-normal"></i></a>
                        <a class="btn btn-lg btn-primary navbar-color btn-lg-square rounded" href="#"><i class="fab fa-instagram fw-normal"></i></a>
                    </div> 
                </div>
          </td>
          <td style="width:20%;">
            </td>

            <td style="width:20%;height:1px">
              <div style="text-align:left;height:100%">
                    <h3  class="text-white mb-4">Radno vreme</h3>
                    <p class="mb-2">Radnim danima: 08:00-20:00</p>
                    <p class="mb-2">Subota: 08:00-20:00</p>
                    <p class="mb-0">Nedelja: zatvoreno</p>
                </div>
            </td>
        </table>
    </div>
</template>

<script>
import shared from './shared'

export default {
  name: 'App',
  mounted: function(){
      this.role=shared.getRoleFromToken();
  },
  data: function(){
    return {
      role: ''
    }
  },
  watch:{
		$route (to, from){
			this.role=shared.getRoleFromToken();
		}
  },
  methods: {
    logout: function(){
      window.sessionStorage.clear()
      this.role = ''
      this.$router.push('/');
    }
  }
  
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}

.navbar-color{
  background-color:#0DE6FD;
}
.footer {
  left: 0;
  bottom: 0;
  width: 100%;
  background-color: #0257D5;
  color: white;
  text-align: center;
}

@keyframes fadeInUp {
    from {
        transform: translate3d(0,40px,0)
    }

    to {
        transform: translate3d(0,0,0);
        opacity: 1
    }
}

@-webkit-keyframes fadeInUp {
    from {
        transform: translate3d(0,40px,0)
    }

    to {
        transform: translate3d(0,0,0);
        opacity: 1
    }
}

.animated {
    animation-duration: 1s;
    animation-fill-mode: both;
    -webkit-animation-duration: 1s;
    -webkit-animation-fill-mode: both
}

.animatedFadeInUp {
    opacity: 0
}

.fadeInUp {
    opacity: 0;
    animation-name: fadeInUp;
    -webkit-animation-name: fadeInUp;
}

@import url(https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css);
@import url(https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css);
@import url(https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css);


@import url(css/style.css);
@import url(https://fonts.googleapis.com/css2?family=Jost:wght@500;600;700&family=Open+Sans:wght@400;600&display=swap);
</style>
