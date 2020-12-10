exports.config = {
  tests: './*_test.js',
  output: './output',
  //test with different browsers
  multiple:{
    basic:{
      browsers:["chrome","safari"]
    }
  },
  helpers: {
    Puppeteer: {
      url: 'http://localhost:8080/Project_01/',
      show: true,
      windowSize: '1200x900'
    }
  },
  include: {
    I: './steps_file.js'
  },
  bootstrap: null,
  mocha: {},
  name: 'e2e',
  plugins: {
    retryFailedStep: {
      enabled: true
    },
    screenshotOnFail: {
      enabled: true
    }
  }
}