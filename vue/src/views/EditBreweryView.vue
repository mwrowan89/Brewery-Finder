<template>
    <form>
        <label for="name" >Name:</label>
        <input type="text" id="name" name="name">
        <label for="type">Type:</label>
        <input type="text" id="type" name="type" v-model="type">
        <label for="abv">ABV:</label>
        <input type="text" id="abv" name="abv" v-model="abv">
        <label for="brewery">Brewery:</label>
        <input type="text" id="brewery" name="brewery" v-model="brewery">
        <label for="description">Description:</label>
        <input type="text" id="description" name="description" v-model="description">
        <button type="submit">Submit</button>
    </form>
</template>

<script>
export default {
  data() {
    return {
        beer: {
        name: "",
        type: "",
        abv: "",
        brewerId: "",
        description: "",
      },
    }
  }
}
</script>


<style scoped>
form{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin-top: 150px;
    width: 100vw;
    color: white;
}
</style>