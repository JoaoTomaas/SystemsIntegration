package dto;

import data.Item;
import data.Utilizador;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemMapper {
    //secalhar mudamos os nomes so para ser fixeee
    //@Mapping(source = "numberOfSeats", target = "seatCount")
    //dizia na net o duarte faz
    //pudemos usar dependacy injections para nao usaar este contrutor
    ItemMapper INSTANCE = Mappers.getMapper( ItemMapper.class );

    ItemDTO itemToItemDto(Item item);
    UtilizadorDTO utilizadorToUtilizadorDTO(Utilizador utilizador);
}
