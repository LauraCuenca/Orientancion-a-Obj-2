### 2.3 Publicaciones

```java
/**
* Retorna los últimos N posts que no pertenecen al usuario user
*/
public List<Post> ultimosPosts(Usuario user, int cantidad) {
        
    List<Post> postsOtrosUsuarios = new ArrayList<Post>();
    for (Post post : this.posts) {
        if (!post.getUsuario().equals(user)) {
            postsOtrosUsuarios.add(post);
        }
    }
        
   // ordena los posts por fecha
   for (int i = 0; i < postsOtrosUsuarios.size(); i++) {
       int masNuevo = i;
       for(int j= i +1; j < postsOtrosUsuarios.size(); j++) {
           if (postsOtrosUsuarios.get(j).getFecha().isAfter(
     postsOtrosUsuarios.get(masNuevo).getFecha())) {
              masNuevo = j;
           }    
       }
      Post unPost = postsOtrosUsuarios.set(i,postsOtrosUsuarios.get(masNuevo));
      postsOtrosUsuarios.set(masNuevo, unPost);    
   }
        
    List<Post> ultimosPosts = new ArrayList<Post>();
    int index = 0;
    Iterator<Post> postIterator = postsOtrosUsuarios.iterator();
    while (postIterator.hasNext() &&  index < cantidad) {
        ultimosPosts.add(postIterator.next());
    }
    return ultimosPosts;
}
```
```Bad Smells generales: reiventar la rueda 
   Refactoring generales: utilizar streams
```
```java
/**
* Retorna los últimos N posts que no pertenecen al usuario user
*/
public List<Post> ultimosPosts(Usuario user, int cantidad) {
        
List<Post> postsOtrosUsuarios = new ArrayList<Post>();
for (Post post : this.posts) {
    if (!post.getUsuario().equals(user)) {
        postsOtrosUsuarios.add(post);
    }
}
        
   // ordena los posts por fecha
postsOtrosUsuarios.sort(
    Comparator.comparing(Post::getFecha).reversed()
);
        
public List<Post> ultimosPosts(Usuario user, int cantidad) {
    return this.posts.stream()
        .filter(post -> !post.getUsuario().equals(user))                    
        .sorted((p1, p2) -> p2.getFecha().compareTo(p1.getFecha()))         
        .limit(cantidad)                                                   
        .collect(Collectors.toList());                                     
  }
}
```