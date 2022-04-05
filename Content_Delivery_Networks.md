# Content Delivery Networks (CDN)
A CDN is a distributed network of servers that can efficiently deliver web content to users. 
CDNs' store cached content on edge servers in point-of-presence (POP) locations that are close to end users, to minimize latency.

- it caches user content at strategically placed physical nodes across the world
- Azure CDN can also accelerate dynamic content, which cannot be cached, by leveraging various network optimizations using CDN POPs.
  For example, route optimization to bypass Border Gateway Protocol (BGP)

- The benefits of using Azure CDN to deliver web site assets include:
  - Better performance and improved user experience for end users, especially when using applications in which multiple 
    round-trips are required to load content
  - Large scaling to better handle instantaneous high loads, such as the start of a product launch event
  - Distribution of user requests and serving of content directly from edge servers so that less traffic is sent to
    the origin server
- The CDN caches the content for a period specified in the request headers from the origin server
- To use Azure CDN you need to create at least one CDN profile, which is a collection of CDN endpoints
- Every CDN endpoint represents a specific configuration of content deliver behavior and access
- To organize your CDN endpoints by internet domain, web application, or some other criteria, you can use multiple profiles
- Because Azure CDN pricing is applied at the CDN profile level, you must create multiple CDN profiles if you want to use a mix of pricing tiers
- Each Azure subscription has default [limits](https://docs.microsoft.com/en-us/azure/azure-resource-manager/management/azure-subscription-service-limits) for the following resources:
  - The number of CDN profiles that can be created
  - The number of endpoints that can be created in a CDN profile
  - The number of custom domains that can be mapped to an endpoint
- Azure Content Delivery Network (CDN) includes four products:
  - Azure CDN Standard from Microsoft
  - Azure CDN Standard from Akamai
  - Azure CDN Standard from Verizon
  - Azure CDN Premium from Verizon


## **Azure CDN Standard for Microsoft Tier**
### Caching rules (in the endpoint menu)
- **Ignore query strings**. This option is the default mode. A CDN POP simply passes the request and any query strings directly 
  to the origin server on the first request and caches the asset. New requests for the same asset will ignore any query 
  strings until the TTL expires
- **Bypass caching for query strings**. Each query request from the client is passed directly to the origin server with no caching
- **Cache every unique URL**. Every time a requesting client generates a unique URL, that URL is passed back to the origin 
  server and the response cached with its own TTL. This final method is inefficient where each request is a unique URL, 
  as the cache-hit ratio becomes low
### Rules engine (in the endpoint menu)
- Although this tier does not have complex caching rules, it has a **Rules engine** based on various elements of the
  request (headers, query strings, path, method, body and so on...), which is very powerful


## **Azure CDN Standard for Verizon** and **Azure Standard for Akamai**
### Caching rules (in the endpoint menu)
- **Global caching rules**
  - Caching rules can be either global (apply to all content from a specified endpoint) or custom
  - Custom rules apply to specific paths and file extensions
  - These are:
    - **Bypass cache** -> obvious
    - **Override** -> always override the TTL specified in the response headers with a value defined in Azure
    - **Set if missing** -> set a specified TTL in Azure, if there is no TTL specified in  the response headers
    - **Not set** -> use only the TTL value specified in the response headers, only if they're present 
- **Query string caching**
  - Query string caching enables you to configure how Azure CDN responds to a query string. 
  - Query string caching has no effect on files that can't be cached
### These tiers do not have a Rules engine
  
## **Azure CDN Premium for Verizon**
### Caching rules (in the endpoint menu)
?
### Rules engine (in the endpoint menu)
It has a **Rules engine**

If you don't set a TTL on a file, Azure CDN sets a default value. However, this default may be overridden if you have 
set up caching rules in Azure. Default TTL values are as follows:
- Generalized web delivery optimizations: seven days
- Large file optimizations: one day
- Media streaming optimizations: one year

You can purge cached content from the edge nodes, which refreshes the content on the next client request. You might purge 
cached content when publishing a new version of a web app or to replace any out-of-date assets.
You can purge content in several ways:
- On an endpoint by endpoint basis, or all endpoints simultaneously should you want to update everything on your CDN at once
- Specify a file, by including the path to that file or all assets on the selected endpoint by checking the Purge All checkbox 
  in the Azure portal
- Based on wildcards (*) or using the root (/)
```
az cdn endpoint purge `
    --content-paths '/css/*' '/js/app.js' `
    --name ContosoEndpoint `
    --profile-name DemoProfile `
    --resource-group ExampleGroup
```

You can also preload assets into an endpoint. This is useful for scenarios where your application creates a large number
of assets, and you want to improve the user experience by prepopulating the cache before any actual requests occur:
```
az cdn endpoint load `
    --content-paths '/img/*' '/js/module.js' `
    --name ContosoEndpoint `
    --profile-name DemoProfile `
    --resource-group ExampleGroup
```

## Geo-filtering
- Geo-filtering enables you to allow or block content in specific countries, based on the country code 
- In the Azure CDN Standard for Microsoft Tier, you can only allow or block the entire site
- With the Verizon and Akamai tiers, you can also set up restrictions on directory paths


