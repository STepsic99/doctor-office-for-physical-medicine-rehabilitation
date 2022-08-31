<template>
  <div class="container-fluid" style="height: 15em;overflow-y: hidden;">
        <div style="height: 15em;overflow-y: hidden;" id="header-carousel" class="carousel slide carousel-fade" data-bs-ride="carousel">
            <div style="height: 15em;overflow-y: hidden;" class="carousel-inner">
                <div style="height: 15em;overflow-y: hidden;" class="carousel-item active">
                    <img  height="100%" width="100%" src="../assets/electricalstimulation.png" alt="Image">
                    <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                        <div class="p-3" style="max-width: 900px;">
                           
                            <div style="font-size:4em;font-weight:bold" class="display-1 text-white mb-md-4 animated zoomIn">O NAMA</div>
                          
                        </div>
                    </div>
                </div>
                <div style="height: 15em;overflow-y: hidden;" class="carousel-item">
                    <img  height="100%" width="100%" src="../assets/laserotherapy.png" alt="Image">
                    <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                        <div class="p-3" style="max-width: 900px;">
                           
                            <div style="font-size:4em;font-weight:bold" class="display-1 text-white mb-md-4 animated zoomIn">O NAMA</div>
                          
                        </div>
                    </div>
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#header-carousel"
                data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#header-carousel"
                data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>      
    </div>



    <div class="container-fluid py-1" style="margin-top:3em">
        <div class="container">
            <div class="row g-5">
                <div class="col-lg-12">
                    <div class="section-title mb-4">
                        
                        <h1 class="display-5 mb-0">Rumska najbolja fizijatrijska ordinacija</h1>
                    </div>
                    <h4 class="text-body fst-italic mb-4">Diam dolor diam ipsum sit. Clita erat ipsum et lorem stet no lorem sit clita duo justo magna dolore</h4>
                    <p class="mb-4">Tempor erat elitr rebum at clita. Diam dolor diam ipsum et tempor sit. Aliqu diam amet diam et eos labore. Clita erat ipsum et lorem et sit, sed stet no labore lorem sit. Sanctus clita duo justo et tempor eirmod magna dolore erat amet</p>
                </div>
               
            </div>
        </div>
    </div>







        <div class="container-fluid py-5">
        <div class="container">
            <div class="row g-5">
              <h6 class="display-5 mb-0" style="margin-top:0">Naš tim</h6>
               
                <div v-for="worker in workers" :key="worker.id" class="col-lg-4 wow slideInUp" data-wow-delay="0.3s">
                    <div class="team-item">
                        <div class="position-relative rounded-top" style="z-index: 1;">
                            <img class="img-fluid rounded-top w-100" src="../assets/person-darker.jpg" alt="">                           
                        </div>
                        <div class="team-text position-relative bg-light text-center rounded-bottom p-4 pt-4">
                            <h4 class="mb-2">{{worker.firstName}} {{worker.lastName}}</h4>
                            <p class="text-primary mb-0">{{worker.type}}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
// @ is an alias to /src
import axios from "axios";

export default {
  name: 'ServicesView',
  data: function(){
    return {
      workers: []
    }
  },
  mounted: function(){
        axios
        .get("http://localhost:8180/api/v1/medical-workers")
        .then((response) =>{
              this.translateRole(response.data)
        })
        .catch(err => {alert("Neuspešna operacija. Kod greške: "+err.response.status)});
  },
  methods: {
      translateRole: function(arrSer){
          for(let i = 0;i < arrSer.length; i++){
            if(arrSer[i].type=='DOCTOR') arrSer[i].type = 'Fizijatar'
            else if(arrSer[i].type=='NURSE') arrSer[i].type = 'Medicinska sestra'
            else if(arrSer[i].type=='PHYSIOTHERAPIST') arrSer[i].type = 'Fizioterapeut'
          }
          this.workers = arrSer;
      }
  }
}
</script>

<style scoped>
.bg-light{
  background-color:#EEF9FF !important
}
</style>
