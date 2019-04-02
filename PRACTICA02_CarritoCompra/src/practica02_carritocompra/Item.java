package practica02_carritocompra;

public class Item{
    private String sku = "";
    private String nombre = "";
    private int exis = 0;
    private int precio = 0;
    public Item(String sku, String nombre, int exis, int precio){
        this.sku = sku;
        this.nombre = sku;
        this.exis = exis;
        this.precio = precio;
    }
    @Override
    public String toString(){
        return "[ "+this.sku+" , "+this.nombre+" , Exis: "+this.exis+", Precio:"+this.precio+" ]";
    }
}
