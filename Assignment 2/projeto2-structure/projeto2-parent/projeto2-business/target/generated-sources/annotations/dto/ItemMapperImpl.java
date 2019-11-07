package dto;

import data.Item;
import data.Utilizador;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-11-07T14:35:23+0000",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_152 (Oracle Corporation)"
)
public class ItemMapperImpl implements ItemMapper {

    @Override
    public ItemDTO itemToItemDto(Item item) {
        if ( item == null ) {
            return null;
        }

        ItemDTO itemDTO = new ItemDTO();

        itemDTO.setImg_path( item.getImg_path() );
        itemDTO.setId( item.getId() );
        itemDTO.setName( item.getName() );
        itemDTO.setCategory( item.getCategory() );
        itemDTO.setCountry_of_origin( item.getCountry_of_origin() );
        itemDTO.setPrice( item.getPrice() );
        itemDTO.setPublished_date( item.getPublished_date() );
        itemDTO.setUtilizador( utilizadorToUtilizadorDTO( item.getUtilizador() ) );

        return itemDTO;
    }

    @Override
    public UtilizadorDTO utilizadorToUtilizadorDTO(Utilizador utilizador) {
        if ( utilizador == null ) {
            return null;
        }

        UtilizadorDTO utilizadorDTO = new UtilizadorDTO();

        utilizadorDTO.setName( utilizador.getName() );
        utilizadorDTO.setAge( utilizador.getAge() );
        utilizadorDTO.setEmail( utilizador.getEmail() );
        utilizadorDTO.setCountry( utilizador.getCountry() );

        return utilizadorDTO;
    }

    @Override
    public List<ItemDTO> listItemToListItemDto(List<Item> item) {
        if ( item == null ) {
            return null;
        }

        List<ItemDTO> list = new ArrayList<ItemDTO>( item.size() );
        for ( Item item1 : item ) {
            list.add( itemToItemDto( item1 ) );
        }

        return list;
    }
}
