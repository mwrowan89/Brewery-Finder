<template>
  <div class="header">
    <img
      src="https://beerconnoisseur.com/sites/default/files/styles/article_770x366/public/articles/2020/understanding_whiskey_in_barrel-aged_beer/whiskey-fb2.jpg?itok=hRDjPOoB"
    />
  </div>
  <div class="info">
    <div id="info">
      <h3>What is a Brewery?</h3>
      <p>
        A brewery is a facility where beer is produced commercially. It's a
        place where various ingredients such as malted barley, hops, yeast, and
        water are combined and processed through brewing techniques to create
        different styles and flavors of beer. Breweries vary in size and scale,
        ranging from small craft breweries to large industrial operations.
      </p>
    </div>
    <div id="info">
      <h3>What are hops?</h3>
      <p>
        Hops are the flowers of the hop plant (Humulus lupulus), a climbing
        perennial vine. They belong to the Cannabaceae family and are primarily
        cultivated for use in brewing beer. Hops contain various compounds,
        including alpha acids, beta acids, essential oils, and polyphenols,
        which contribute to their characteristic flavor, aroma, and bitterness.
      </p>
    </div>
  </div>
  <div class="history">
    <h3>History of Breweries</h3>
  </div>
  <div class="history">
    <div id="history">
      <h4>Ancient Beginnings</h4>
      <br />
      <p>
        Brewing beer likely originated in ancient Mesopotamia (modern-day Iraq)
        around 4000 BCE. Early civilizations such as the Sumerians and
        Babylonians brewed beer using barley, wheat, and other grains. Beer
        production was also prominent in ancient Egypt, where it was consumed by
        both commoners and royalty. Archaeological evidence suggests that beer
        brewing was a sophisticated process in ancient Egyptian society.
      </p>
    </div>
    <div id="history">
      <h4>Medieval Era</h4>
      <br />
      <p>
        During the Middle Ages, brewing beer became closely associated with
        monasteries in Europe. Monks brewed beer as a way to support themselves
        and provide sustenance for travelers and pilgrims. Monastic breweries
        played a significant role in advancing brewing techniques and developing
        new beer styles. In 1516, the Bavarian Beer Purity Law (Reinheitsgebot)
        was enacted in Germany, specifying that beer could only be brewed using
        water, barley, and hops (yeast was later added). This law standardized
        beer production and ensured the quality of beer brewed in Bavaria.
      </p>
    </div>
    <div id="history">
      <h4>Industrial Revolution</h4>
      <br />
      <p>
        The Industrial Revolution brought significant changes to the brewing
        industry in the 18th and 19th centuries. Advances in technology, such as
        steam power and refrigeration, enabled larger-scale production and
        distribution of beer. The invention of lager beer, a bottom-fermented
        beer style that requires cold fermentation and storage, revolutionized
        the brewing industry in the 19th century. Lager beer became immensely
        popular worldwide and led to the establishment of large-scale commercial
        breweries.
      </p>
    </div>
    <div id="history">
      <h4>Prohibition and Consolidation</h4>
      <br />
      <p>
        In the early 20th century, the temperance movement and Prohibition laws
        in countries such as the United States led to the temporary closure of
        many breweries. Prohibition had a significant impact on the brewing
        industry, causing some breweries to shut down permanently while others
        shifted to producing non-alcoholic beverages. Following Prohibition, the
        brewing industry underwent consolidation, with many smaller breweries
        being acquired or forced out of business by larger brewing
        conglomerates. This trend continued into the late 20th century, leading
        to a decline in the number of independent breweries.
      </p>
    </div>
  </div>
  <div class="craft">
    <h3>Craft Beer Renaissance</h3>
  </div>
  <div class="craftBeer">
     <div id="craft">
    <h4>Craft Beer Movement</h4>
    <br />
    <p>
      In the late 20th century, there was a resurgence of interest in
      traditional brewing methods and artisanal beer styles. The craft beer
      movement emerged, with small, independent breweries producing innovative
      and high-quality beers with distinctive flavors and ingredients. The craft
      beer movement spread globally, leading to a proliferation of
      microbreweries, brewpubs, and craft beer bars in countries around the
      world. Craft brewers focus on creativity, quality, and community
      engagement, challenging the dominance of mass-produced beer brands.
    </p>
  </div>
  <img src="https://20thcenturyhistorylife.weebly.com/uploads/5/0/8/6/50862903/1549466_orig.jpg">
  </div>
 
</template>

<style scoped>
.info {
  display: flex;
  justify-content: space-around;
}
#info {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  white-space: wrap;
  border: 2px solid white;
  border-radius: 4px;
  margin: 1vw 2vw 2vw 2vw;
  padding: 20px;
}
.history {
  display: flex;
  justify-content: center;
  align-items: center;
}
#history {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  white-space: wrap;
  height: max-content;
  border: 2px solid white;
  border-radius: 4px;
  margin: 1vw 2vw 2vw 2vw;
  padding: 20px;
}
.craft {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-left: -30vw;
}
#craft {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  white-space: wrap;
  height: max-content;
  border: 2px solid white;
  border-radius: 4px;
  margin: 1vw 5vw 5vw 10vw;
  padding: 20px;
}
.craftBeer {
  display: flex;
  align-items: center;
  margin-bottom: 5vw;
}
.craftBeer img {
  height: 30vh;
  width: 30vw;
  margin-right: 15vw;
  margin-top: -7vw;
}
.header img {
  height: 90vh;
  width: 100vw;
  margin-top: 10vh;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
