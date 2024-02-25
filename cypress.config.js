const { defineConfig } = require('cypress')

module.exports = defineConfig({
  viewportHeight: 1080, 
  viewportWidth: 1920,
  video: false,
  e2e: {
    baseUrl: 'http://localhost:4200',
    excludeSpecPattern: ['**/1-getting-started', '**/2-advanced-examples'],
    specPattern: 'cypress/e2e/**/*.{js,jsx,ts,tsx}',
    
    setupNodeEvents(on, config) {
      // implement node event listeners here
    },
  },
  env: {
    randomNumber: (Math.random().toString().substr(2, 5)),
    bearerToken: 'e8becec28e930adc109045a099b432f8fa8019bd4d74d927771bce4b98f7f274', 
  }

});
