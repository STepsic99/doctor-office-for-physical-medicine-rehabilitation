<template>
   
      <div class="container-fluid" style="height: 15em;overflow-y: hidden;">
        <div style="height: 15em;overflow-y: hidden;" id="header-carousel" class="carousel slide carousel-fade" data-bs-ride="carousel">
            <div style="height: 15em;overflow-y: hidden;" class="carousel-inner">
                <div style="height: 15em;overflow-y: hidden;" class="carousel-item active">
                    <img  height="100%" width="100%" src="../assets/electricalstimulation.png" alt="Image">
                    <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                        <div class="p-3" style="max-width: 900px;">
                           
                            <div style="font-size:4em;font-weight:bold" class="display-1 text-white mb-md-4 animated zoomIn">CENOVNIK USLUGA </div>
                          
                        </div>
                    </div>
                </div>
                <div style="height: 15em;overflow-y: hidden;" class="carousel-item">
                    <img  height="100%" width="100%" src="../assets/laserotherapy.png" alt="Image">
                    <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                        <div class="p-3" style="max-width: 900px;">
                           
                            <div style="font-size:4em;font-weight:bold" class="display-1 text-white mb-md-4 animated zoomIn">CENOVNIK USLUGA </div>
                          
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
    <div style="padding:0.75em;font-size:1.5em;margin-top:3em;margin-bottom:3em">
      <table style="margin: 0 auto;margin-bottom:2em" width="50%">
      <tr class='row-border'>
        <th colspan="2" style="text-align:left;color: #0DE6FD;">Pregledi</th>
        </tr>
      <tr v-for="exam in doctorServices" :key="exam.id">
        <td style="text-align:left;font-weight:bold">
          {{exam.name.charAt(0)+exam.name.substring(1).toLowerCase()}}
        </td>
        <td style="text-align:right;color: #0DE6FD;font-weight:bold">
          {{exam.price}} din
        </td>
        </tr>
      </table>
     <table style="margin: 0 auto;" width="50%">
      <tr class='row-border'>
        <th colspan="2" style="text-align:left;color: #0DE6FD;">Fizikalne terapije</th>
        </tr>
      <tr v-for="ther in physiotherapistServices" :key="ther.id">
        <td style="text-align:left;font-weight:bold">
          {{ther.name.charAt(0)+ther.name.substring(1).toLowerCase()}}
        </td>
        <td style="text-align:right;color: #0DE6FD;font-weight:bold">
          {{ther.price}} din
        </td>
        </tr>
      </table>  
  </div>
</template>

<script>
// @ is an alias to /src
import axios from "axios";

export default {
  name: 'ServicesView',
  data: function(){
    return {
      services: [],
      doctorServices: [],
      physiotherapistServices: []
    }
  },
  mounted: function(){
        axios
        .get("http://localhost:8180/api/v1/services")
        .then((response) =>{
              this.splitServices(response.data)
        })
        .catch(err => {alert("Neuspešna operacija. Kod greške: "+err.response.status)});
  },
  methods: {
      splitServices: function(arrSer){
          for(let i = 0;i < arrSer.length; i++){
            if(arrSer[i].staffType=='DOCTOR') this.doctorServices.push(arrSer[i])
            else this.physiotherapistServices.push(arrSer[i])
          }
      }
  }
}
</script>

<style scoped>

tr.row-border { 
  border-bottom: solid #0DE6FD;
 
}

tr:not(.row-border){
  border: solid lightgray;
  border-width: 0.2px 0;
}

td{
  padding-top:0.5em;
  padding-bottom:0.5em
}

</style>

