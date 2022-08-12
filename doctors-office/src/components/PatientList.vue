<template>
<div v-if="existingPatient">
<form v-if="!personChosen" class="row d-flex justify-content-between">
  <div class="col-11">
  <div class="input-group">
<select class="form-select" v-model="searchOption" id="inputGroupSelect01">   
    <option value="1">Ime i prezime</option>
    <option value="2">JMBG</option>
  </select>
  <input type="text" class="form-control">
  <input v-if="searchOption==1" type="text" class="form-control">
</div>
  </div>
  <div class="col-1">
    <button type="button" class="btn btn-primary mb-3" v-on:click="searchPeople()">Pretraži</button>
  </div>
</form>
<div v-if="showList">
<table style="width: 100%;">
  <tbody>
  <tr>
    <td>
      <div class="thumb">
                    <img class="img-fluid" src="../assets/person.png" alt="">
      </div>
    </td>
    <td>
      <div>
      <h5 class="mb-0" style="color:#0d6efd;margin-bottom:1em">Ime Prezime</h5>
      <i class="fas fa-id-card" aria-hidden="true"></i> 0810999122333
      </div>
    </td>
    <td>
      <i v-if="personChosen" class="fa fa-times" aria-hidden="true" style="color:red;zoom:2;margin-left:60%;cursor: pointer;" v-on:click="rechoosePerson()"></i>
      <button v-if="!personChosen" type="button" class="btn btn-primary" v-on:click="choosePerson()">Izaberi</button>
    </td>
  </tr>
  </tbody>
</table>
</div>
</div>
<div v-else>
  <form class="row d-flex justify-content-between">
  <div class="col-6">
   <input type="text" class="form-control" placeholder="Ime">
  </div>
  <div class="col-6">
    <input type="text" class="form-control" placeholder="Prezime">
  </div>
</form>
<form style="margin-top: 1.8em;" class="row d-flex justify-content-between">
  <div class="col-6">
   <input type="text" class="form-control" placeholder="Broj telefona">
  </div>
  <div class="col-6">
    <input type="text" class="form-control" placeholder="JMBG (opciono)">
  </div>
</form>
</div>
</template>

<script>
import Datepicker from 'vue3-date-time-picker';
import 'vue3-date-time-picker/dist/main.css'
export default {
  name: 'PatientList',
  components: {Datepicker},
  props: {
    existingPatient: Boolean
  },
   data: function(){
    return {
        showList: false,
        personChosen: false,
        searchOption:1
    }
  },
  methods: {
    searchPeople: function(){
      this.showList=true;
    },
     choosePerson: function(){
      this.personChosen=true;
    },
    rechoosePerson: function(){
      this.personChosen=false;
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.thumb {
   margin-left:15%;
    width: 80px;
    height: 80px;
    -o-object-fit: cover;
    object-fit: cover;
    overflow: hidden;
    border-radius: 50%;
}
</style>
