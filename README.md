If you want auto-generated numeric IDs:
Change the type to Long:

java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
private Long id;
If you want to use UUID values (String):
Remove the @GeneratedValue annotation and set id manually in your service class (as you did with UUID.randomUUID()):

java
@Id
@Column(name = "id")
private String id;
And in your service’s create method:

java
rating.setId(UUID.randomUUID().toString());