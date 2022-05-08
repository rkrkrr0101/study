console.log('req')
module.exports='나를찾아'
require('./var')

console.log('req.cache')
console.log(require.cache)
console.log('req.main')
console.log(require.main===module)
console.log(require.main.filename)