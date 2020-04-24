# bsp-common-lib [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
![Java CI](https://github.com/hmcts/bsp-common-lib/workflows/Java%20CI/badge.svg)

Common code for implementing Bulk Scanning

## Deployment

Update the version number in build.gradle when making a change as the library will automatically be deployed upon merging to master. (Also remember to bump the version number for bsp-common-lib in both Divorce COS & FR COS)

### Working locally

To make sure the in-development version of this is used by the project that depends on this library, please use Gradle's composite build feature (found in the Gradle toolbar in IntelliJ).

## Testing end-to-end with BSP

If you need to perform an end-to-end test with BSP, please have a look into this article for more information:
- https://tools.hmcts.net/confluence/display/BSP/How+to+test+BSP+end+to+end

## Versioning

We use [SemVer](http://semver.org/) for versioning.
For the versions available, see the tags on this repository.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.
