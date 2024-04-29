<template>
    <div class="top">
        <div class="image">
            <div class="text">
              <h1>BREWERY FINDER!</h1>
              <p>Locate a brewery near you by rating or type</p>  
            </div>
            
        </div>
    </div>
</template>

<style scoped>
@font-face {
    font-family: 'font';
    src: url('/vue/src/assets/The-Lobster-Font/Lobster.woff2') format('woff2'),
        url('/vue/src/assets/The-Lobster-Font/Lobster.woff') format('woff');
}
.text {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding-top: 180px;
    padding-bottom: 200px;
    color: rgb(170, 130, 78);
    background-color: rgba(255, 255, 255, 0.44);
}
.text h1 {
    font-size: 100px;
    font-family: 'font';
}
.text p {
    font-size: 50px;
}
.image {
    height: 60vh;
    width: 100vw;
    background-image: url("https://cdn.brewersassociation.org/wp-content/uploads/2020/06/small-brewery-demographics2.jpg");
    background-position: center;
    margin-top: 7%;
}

</style>