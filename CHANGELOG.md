# Changelog

Todas as alterações dignas de nota neste projeto serão documentadas neste arquivo.

## 0.0.1

* Creation of a CryptoUtil class, to create a MD5 digest of a string.
* Creation of a RequestUtil class, to extract the IP of a request and to extract the OAuth2 client login from the Authorization header.
* Creation of a CommomServerProperties class. This class loads properties from a external json file, located by a TomCat path variable. An example of this jason is in the /properties-files folder.
* Creation of a ApplicationPropertiesInternal class. This class loads properties from a internal (project classpath) json. An example of this jason is in the /properties-files folder.