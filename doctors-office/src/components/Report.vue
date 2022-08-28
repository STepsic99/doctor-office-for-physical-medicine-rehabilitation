<template>
<div style="border: 1px solid;border-color:  #ced4da;">
      <div style="text-align:left;margin-top:5%;margin-bottom:5%;margin-left:5em;margin-right:5em">
        Ime i prezime: <b>{{this.$store.state.appointment.patientFirstName}} {{this.$store.state.appointment.patientLastName}}</b>  <span style="float:right">
       Datum pregleda: {{transformDate(this.$store.state.appointment.start)}}
      </span>
      <br>
        JMBG: {{this.$store.state.appointment.patientPersonalID}}
      </div>
      <h3 style="margin-left:5em;"> IZVEŠTAJ LEAKRA SPECIJALISTE FIZIKALNE MEDICINE </h3>
      <table width="100%" style="margin-top:5%;border-collapse:separate; border-spacing:5em 0;margin-bottom:5%">
        <tr>
          <td style="text-align:left;vertical-align: top;padding-bottom:5em" width="10%">Anamneza: </td>
          <td style="padding-bottom:5em;text-align:left" width="90%">{{this.$store.state.appointment.report.anamnesis}}</td>
        </tr>
        <tr>
          <td style="text-align:left;vertical-align: top;padding-bottom:5em" width="10%">Status: </td>
          <td style="padding-bottom:5em;text-align:left" width="90%">{{this.$store.state.appointment.report.status}}</td>
        </tr>
        <tr>
          <td style="text-align:left;vertical-align: top;padding-bottom:5em" width="10%">Nalazi: </td>
          <td style="padding-bottom:5em;text-align:left" width="90%">{{this.$store.state.appointment.report.medicalFindings}}</td>
        </tr>
        <tr>
          <td style="text-align:left;vertical-align: top;padding-bottom:5em" width="10%">Plan lečenja: </td>
          <td style="padding-bottom:5em;text-align:left" width="90%">{{this.$store.state.appointment.report.treatmentPlan}}</td>
        </tr>
        <tr>
          <td style="text-align:left;vertical-align: top;padding-bottom:5em" width="10%">Kontrola: </td>
          <td style="padding-bottom:5em;text-align:left" width="90%">{{this.$store.state.appointment.report.followUpExamination}}</td>
        </tr>
        <tr>
          <td style="text-align:left;" width="10%">Dijagnoze: </td>
          <td width="90%" style="text-align:left;"></td>
        </tr>
        <tr>
          <td colspan="2" style="text-align:left;">
            <div v-for="diag in this.$store.state.appointment.report.diagnoses" :key="diag.id">
                {{diag.code}} - {{diag.name}}
            </div>
          </td>
        </tr>
        </table>
        <div v-if="this.$store.state.appointment.report.therapies.length!=0" style="text-align:center;background-color:#e9ecef;margin-left:5em;margin-right:5em"><h5 style="margin-bottom:0"><b>NALOG ZA PRUŽANJE TERAPIJE</b></h5></div>
        <div v-if="this.$store.state.appointment.report.therapies.length!=0" style="margin-right:5em;margin-left:5em;margin-top:1em;margin-bottom:5%;">
        <table class="service-table" width="100%">
          <tr style="background-color:#e9ecef;">
            <th>Naziv usluge</th>
            <th>Napomena</th>
            </tr>
            <tr v-for="therapy in this.$store.state.appointment.report.therapies" :key="therapy.id">
              <td>
                {{therapy.service.name}}
              </td>
              <td>
                {{therapy.note}}
              </td>
              </tr>
        </table>
        </div>
        <div style="text-align:right;margin-right:5em;margin-top:6em">
       dr {{this.$store.state.appointment.doctorFirstName}} {{this.$store.state.appointment.doctorLastName}}, Spec. fizikalne medicine i rehabilitacije
      </div>
</div>

</template>

<script>
export default {
  name: 'Report',
  data: function(){
    return {
      
    }
  },
  methods: {
    transformDate(rawDate){
        rawDate = new Date(rawDate)
        return rawDate.getDate()+"."+(rawDate.getMonth()+1)+"."+rawDate.getFullYear()+"."+" "+rawDate.getHours()+":"+(rawDate.getMinutes()<10?'0':'') +rawDate.getMinutes()
    }
  }
}
</script>

<style scoped>

.service-table, table.service-table  th, table.service-table td{
   border: 1px solid black;
  border-collapse: collapse;
}
</style>
