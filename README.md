# feup-SDIS

# Distributed Systems

## [Distributed Backup Service](https://web.fe.up.pt/~pfs/aulas/sd2018/projs/proj1/proj1.html)
In this project we develop a distributed backup service for a local area network (LAN). The idea is to use the free disk space of the computers in a LAN for backing up files in other computers in the same LAN. The service is provided by servers in an environment that is assumed cooperative (rather than hostile). Nevertheless, each server retains control over its own disks and, if needed, may reclaim the space it made available for backing up other computers' files.

### Running

In order to start the `rmiregistry`, use the following command

```
$ javac *.java
```
On windows:

```
$ rmiregistry 
```

On linux:

```
$ rmiregistry &
```

To start a peer, use the following command as an example:

```
$ java Peer <PeerID>
```
Where `<PeerID>` is a number that corresponds to the id of a peer.
  
To start the App, use the following command as an example:
```
$ java App 1 backup <file> <reprlicationDegree>
```

Your App is supposed to trigger the backup of file test1.pdf with a replication degree of 3. Likewise, by invoking:
```
$ java App 1 backup test1.pdf 3
```

### Members ###

* Francisca Leão Cerquinho R. Fonseca, up201505791@fe.up.pt
* Ventura Pereira, up201404690@fc.up.pt
