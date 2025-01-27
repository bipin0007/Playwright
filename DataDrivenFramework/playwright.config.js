const { defineConfig } = require('@playwright/test');

module.exports = defineConfig({
  testDir: '/Users/bipinkumar/Documents/DataDrivenFram/DataDrivenFramework/src',
  reporter: [['html', { outputFolder: 'reports' }]],
});


