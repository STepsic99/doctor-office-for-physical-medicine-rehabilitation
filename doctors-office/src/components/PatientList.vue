<template>
<div>
<form class="row d-flex justify-content-between g-3 ">
  <div class="col-5">
   <input type="text" class="form-control" placeholder="Ime">
  </div>
  <div class="col-5">
    <input type="text" class="form-control" placeholder="Prezime">
  </div>
  <div class="col-2">
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
      <h5 class="mb-0" style="color:#0d6efd;">Ime Prezime</h5>
      <i class="fa fa-phone" aria-hidden="true"></i> 0000000
      </div>
    </td>
    <td>
      <button type="button" class="btn btn-primary">Izaberi</button>
    </td>
  </tr>
  </tbody>
</table>
</div>
</div>
</template>

<script>
export default {
  name: 'PatientList',
  props: {
    msg: String
  },
   data: function(){
    return {
        showList: false
    }
  },
  methods: {
    searchPeople: function(){
      this.showList=true;
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
