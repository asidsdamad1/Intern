package validators;


import model.Furniture;

public class FurnitureValidator extends ThingValidator{

    public String validate(Furniture entity) {

        if(entity.getName().length() <= 0)
            resultValidate = "name can not be null";

        return resultValidate;
    }
}
