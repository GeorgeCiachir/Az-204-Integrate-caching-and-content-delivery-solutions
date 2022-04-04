# Explore Azure Cache for Redis
Azure Cache for Redis provides an in-memory data store based on the Redis software.
Azure Cache for Redis offers:
- the **Redis open-source (OSS Redis)** 
- a commercial product from **Redis Labs (Redis Enterprise)** as a managed service

![img.png](img.png)

![img_1.png](img_1.png)

## Configure Azure Cache for Redis
- Put the Redis cache as close to the data consumer as you can.
- Three pricing tiers available for an Azure Cache for Redis:
  - **Basic**: Basic cache ideal for development/testing. Is limited to a single server, 53 GB of memory, and 20,000 connections. 
    There is no SLA for this service tier
  - **Standard**: Production cache which supports replication and includes an SLA. It supports two servers, and has the
    same memory/connection limits as the Basic tier
  - **Premium**: Enterprise tier which builds on the Standard tier and includes persistence, clustering, and scale-out 
    cache support. This is the highest performing tier with up to 530 GB of memory and 40,000 simultaneous connections
- You can control the amount of cache memory available on each tier - this is selected by choosing a cache level 
  from C0-C6 for Basic/Standard and P0-P4 for Premium
- The Premium tier allows you to persist data in two ways to provide disaster recovery:
  - RDB persistence takes a periodic snapshot and can rebuild the cache using the snapshot in case of failure.
  - AOF persistence saves every write operation to a log that is saved at least once per second. This creates bigger files
    than RDB but has less data loss
- Some benefits of using the premium tier:
  - you can deploy the cache to a virtual network in the cloud and the cache will be available to only other virtual machines
    and applications in the same virtual network.
  - you can implement clustering to automatically split your dataset among multiple nodes. To implement clustering, you
    specify the number of shards to a maximum of 10. The cost incurred is the cost of the original node, multiplied by 
    the number of shards

## Accessing the Redis instance
- Redis has a command-line tool for interacting with an Azure Cache for Redis as a client

![img_2.png](img_2.png)

When the TTL elapses, the key is automatically deleted, exactly as if the DEL command were issued. Here are some notes
on TTL expirations:
- Expirations can be set using seconds or milliseconds precision
- The expire time resolution is always 1 millisecond
- Information about expires are replicated and persisted on disk, the time virtually passes when your Redis server 
  remains stopped (this means that Redis saves the date at which a key will expire)
