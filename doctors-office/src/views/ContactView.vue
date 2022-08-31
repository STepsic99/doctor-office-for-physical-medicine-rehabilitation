<template>
  <div class="container-fluid" style="height: 15em;overflow-y: hidden;">
        <div style="height: 15em;overflow-y: hidden;" id="header-carousel" class="carousel slide carousel-fade" data-bs-ride="carousel">
            <div style="height: 15em;overflow-y: hidden;" class="carousel-inner">
                <div style="height: 15em;overflow-y: hidden;" class="carousel-item active">
                    <img  height="100%" width="100%" src="../assets/electricalstimulation.png" alt="Image">
                    <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                        <div class="p-3" style="max-width: 900px;">
                           
                            <div style="font-size:4em;font-weight:bold" class="display-1 text-white mb-md-4 animated zoomIn">Kontakt</div>
                          
                        </div>
                    </div>
                </div>
                <div style="height: 15em;overflow-y: hidden;" class="carousel-item">
                    <img  height="100%" width="100%" src="../assets/laserotherapy.png" alt="Image">
                    <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                        <div class="p-3" style="max-width: 900px;">
                           
                            <div style="font-size:4em;font-weight:bold" class="display-1 text-white mb-md-4 animated zoomIn">Kontakt</div>
                          
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

    <div style="margin-top:3em">
        <h2>Adresa</h2><p>
            123 Glavna, Ruma, Srbija <br/>
            vsrelax@example.com <br/></p>
        <h2>Telefon</h2> 
        <p>+012 345 67890</p>
        <h2>Radno vreme</h2> 
        <p>Radnim danima: 08:00-16:00 Subota: 08:00-13:00 Nedelja: zatvoreno</p>
    </div>








      
</template>

<script>
// @ is an alias to /src


export default {
  name: 'ContactView',
}
</script>

<style scoped>
</style>
