# Keycloak Jax-RS Example
A simple example of REST service that uses external Keycloak authentication.

In the example we will use OpenID Connect to secure the REST interface based on the `keycloak.json`, another method it is possible via WildFly `standalone.xml`, please refer to this [page](https://www.keycloak.org/docs/latest/securing_apps/#openid-connect-3) for more info.

## Keycloak setup
The esiest way is to use a Docker container, so run:

```bash
docker run -d --name kc -p 8085:8080 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin quay.io/keycloak/keycloak:9.0.3
```

to start Keycloak deployed in a WildFly container. To login connect to http://localhost:8085/auth/admin and use `admin/admin` credentials.
Create a new `realm` called `marvel`.

Under `Client` add a new one called `farm-application` and as `Root url` put http://localhost:8080/farm-application/api/. Finally create a user along with his password.

## Run the farm application

### Setup WildFly to deploy the farm application
[Download](https://downloads.jboss.org/keycloak/9.0.3/adapters/keycloak-oidc/keycloak-wildfly-adapter-dist-9.0.3.zip) and install the WildFly adapter as explained in the [doc](https://www.keycloak.org/docs/latest/securing_apps/#jboss-eap-wildfly-adapter).

#### Module installation
After the module installation, the server is ready to manage and Open ID authentication:

```xml
<mechanism mechanism-name="KEYCLOAK">
    <mechanism-realm realm-name="KeycloakOIDCRealm" realm-mapper="keycloak-oidc-realm-mapper"/>
</mechanism>
```
the mechanism name will be reused into the `web.xml` file.

#### Disclaimer
In the example Keycloak version `9.0.3` is used, version `10.0.0` is also available. WildFly 11.0.0 is deprected but for this simple example it works great.

### Generate the `.war` file
Clone the repository, produce the `.war` file and deploy it into your WildFly 11.0.0 or greater.

#### `keycloak.json`
If you change some parameters like the port or the client name, you have the edit the configuration file accordingly.

## Connect to the service
Connect to http://localhost:8080/farm-application/api/products to display some products.

You should be redirect to keycloak and once authenticated, redericted to the products `json`.

## References
- [Keycloak Documentation](https://www.keycloak.org/documentation)
- [Keycloak OpenID](https://www.keycloak.org/docs/latest/securing_apps/#openid-connect-3)
- [Keycloak on Docker](https://www.keycloak.org/getting-started/getting-started-docker)
- [Keycloak Docker image](https://github.com/keycloak/keycloak-containers/blob/master/server/README.md)
- [Keycloak QuickStarts projects](https://github.com/keycloak/keycloak-quickstarts)
- [Keycloak basic deployment](https://www.keycloak.org/docs/latest/getting_started/index.html#_install-boot)
- [Authentication code flows](https://openid.net/specs/openid-connect-core-1_0.html#CodeFlowAuth)
- [A tutorial with example](https://medium.com/@siweheee/keycloak-a-real-scenario-from-development-to-production-ce57800e3ba9)
