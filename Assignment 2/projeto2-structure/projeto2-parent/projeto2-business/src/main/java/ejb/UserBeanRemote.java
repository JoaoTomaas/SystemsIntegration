package ejb;

import javax.ejb.Remote;
import java.util.Date;

@Remote
public interface UserBeanRemote {
    //NOTA: Só o dono do item é que vai poder editar a info do mesmo, logo temos que verificar
    //se o id do user da sessao e igual ao do dono do item

    //PROCURAR ITEMS
    public void Procurar_Items(); //Procurar todos os items
    public void Procurar_Items_Categoria(String categoria);
    public void Procurar_Items_Country(String pais);
    public void Procurar_Items_PriceRange(int lower_bound, int upper_bound);
    public void Procurar_Items_Data(Date data); //Inserir o atributo Data na Entidade Item

    //ITEM OPERATIONS
    /*public void Listar_Items_aVenda(); //Listar todos os items que tenho para venda, ordenados por data de insercao
    public void Inserir_Novo_Item(String name, String category, String country_of_origin); //Falta a fotografia
    public void Editar_Item_Info(String name, String category, String country_of_origin); //Falta a fotografia

    //USER OPERATIONS
    public void Editar_User_Info(String name, int age, String email, String country);
    public void Delete_UserAccount(); //Apagar primeiro os items do user e depois apagar o user
    */
}

