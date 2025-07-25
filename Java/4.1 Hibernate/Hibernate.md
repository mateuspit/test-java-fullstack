## Hibernate e ORM

**Hibernate** é um framework **ORM (Object-Relational Mapping)** que facilita muito a vida do desenvolvedor Java ao lidar com bancos de dados relacionais. Em vez de escrever SQL manualmente para inserir, buscar ou atualizar dados, você trabalha diretamente com objetos Java, e o Hibernate cuida de mapear esses objetos para as tabelas do banco. 

Por exemplo, se você tem uma classe **Item** com atributos como **name** e **price**, basta anotá-la com `@Entity` e `@Table`, e o Hibernate entende que ela representa uma tabela no banco. A principal vantagem disso é que você foca mais na lógica de negócio e menos em código repetitivo de persistência, além de ganhar recursos como gerenciamento de transações, relacionamentos entre entidades e cache, tudo de forma transparente. Isso acelera o desenvolvimento e ajuda a manter o código limpo e organizado.

### Exemplo de Código com Hibernate:

```java
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "items") // Aqui definimos o nome da tabela no banco
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;

    public Item() {}

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
}
```