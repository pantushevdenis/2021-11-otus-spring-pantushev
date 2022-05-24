package ru.otus.spring.pantushev.domain.dropdownLists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import ru.otus.spring.pantushev.domain.Printable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author
    implements Printable
{
    @Id
    private ObjectId id;
    private String shortName;

    public static String getHead() {
        return bar +
            "ID\tshortName\n" +
            bar;
    }

    @Override
    public String getLine() {
        return String.format("%s\t%s\n", this.id.toString(), this.shortName);
    }

    private static final String bar = "----------------------------------------------------\n";
}
