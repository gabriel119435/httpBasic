## httpBasic
* demo of http basic authentication with spring security
* users and their authorities are externalized in `application.yml`.

## users
| username | password | authorities        |
|----------|----------|--------------------|
| admin    | admin    | SERVICE1, SERVICE2 |
| user     | pass     | SERVICE1           |

## endpoints
| endpoint            | protection                                                           |
|---------------------|----------------------------------------------------------------------|
| `GET /random`       | any authenticated user                                               |
| `GET /seriousInfo1` | SERVICE2 authority only — enforced in `SecurityConfig`               |
| `GET /seriousInfo2` | SERVICE2 authority only — enforced on the method via `@PreAuthorize` |

## how to call
set the `authorization` header to `Basic {base64(username:password)}`.