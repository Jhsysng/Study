package hello.itemservice.web.validation.form;

import hello.itemservice.domain.item.Item;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.net.URLPermission;

@Data
public class ItemUpdateForm {
    @NotNull(groups = URLPermission.class)
    private Long id;

    @NotBlank
    private String itemName;

    @NotNull
    @Range(min = 1000, max = 1000000)
    private Integer price;

    // 수정 시에는 자유롭게 변경 가능
    private Integer quantity;

    public Item toEntity() {
        return new Item(itemName, price, quantity);
    }
}
