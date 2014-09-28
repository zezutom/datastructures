var gulp = require('gulp');
var mocha = require('gulp-mocha');
var gutil = require('gulp-util');

gulp.task('build', function() {
    return gulp.src('./src/test/js/*test.js')
        .pipe(mocha({reporter: 'spec'}).on('error', gutil.log));
});
