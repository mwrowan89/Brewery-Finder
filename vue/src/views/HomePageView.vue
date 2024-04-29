<template>
    <div class="top">
        <div class="image"></div>
        <div class="text">
            <h2>Welcome to the </h2>
              <h1>Ale Atlas</h1>
              <p>Locate a brewery near you by rating or type</p>  
            </div>
    </div>
</template>

<style scoped>
@font-face {
  font-family: 'monthoers';
  src: url('Fonts/Monthoers.ttf');
}
@font-face {
    font-family: 'hughs';
    src: url('Fonts/Hughs.woff');
}

.top {
    background-color: white;
}

.text {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    color: rgb(170, 130, 78);
    background-color: rgba(255, 255, 255, 0.671);
    margin-top: -28vw;
    padding: 20px;
    
}
.text h1 {
    font-size: 100px;
    font-family: 'monthoers';
    font-weight: 800;
    color: rgba(27, 26, 26, 0.829);
}
.text h2 {
    font-family: 'hughs';
    font-size: 40px;
}
.text p {
    font-size: 50px;
    font-family: 'hughs';
}
.image {
    height: 60vh;
    width: 100vw;
    background-image: url("https://cdn.brewersassociation.org/wp-content/uploads/2020/06/small-brewery-demographics2.jpg");
    background-position: center;
    margin-top: 5%;
}

</style>