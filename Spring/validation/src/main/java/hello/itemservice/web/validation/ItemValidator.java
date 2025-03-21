package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, org.springframework.validation.Errors errors) {
        Item item = (Item) target;

        //검증 로직
        if(!org.springframework.util.StringUtils.hasText(item.getItemName())){
            errors.rejectValue("itemName", "required");
        }

        if(item.getPrice()==null || item.getPrice()<1000 || item.getPrice() > 1000000){
            errors.rejectValue("price", "range", new Object[]{1000, 1000000}, null);
        }

        if(item.getQuantity() == null || item.getQuantity() >= 9999){
            errors.rejectValue("quantity", "max", new Object[]{9999}, null);
        }

        //특정 필드가 아닌 복합 룰 검증
        if(item.getPrice() != null && item.getQuantity() != null){
            int result = item.getPrice() * item.getQuantity();
            if(result < 10000){
                //글로벌 오류
                errors.reject("totalPriceMin", new Object[]{10000, result}, null);
            }
        }
    }
}
