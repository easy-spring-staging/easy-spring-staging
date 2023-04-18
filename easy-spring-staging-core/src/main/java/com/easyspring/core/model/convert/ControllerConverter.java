package com.easyspring.core.model.convert;

import com.easyspring.core.model.Page;
import com.easyspring.core.model.dto.AbstractDTO;
import com.easyspring.core.model.vo.AbstractAddVO;
import com.easyspring.core.model.vo.AbstractDetailVO;
import com.easyspring.core.model.vo.AbstractEditVO;
import com.easyspring.core.model.vo.AbstractListVO;

import java.util.List;

public interface ControllerConverter<K, DV extends AbstractDetailVO<K>, LV extends AbstractListVO<K>, AV extends AbstractAddVO<Void>, EV extends AbstractEditVO<K>,DTO extends AbstractDTO<K> >{
    DTO dvToDto(DV v);
    List<DTO> dvToDto(List<DV> v);
    DTO lvToDto(LV v);
    List<DTO> lvToDto(List<LV> v);
    DTO avToDto(AV v);
    List<DTO> avToDto(List<AV> v);
    DTO evToDto(EV v);
    List<DTO> evToDto(List<EV> v);
    DV dtoToDv(DTO dto);
    List<DV> dtoToDv(List<DTO> dto);
    LV dtoToLv(DTO dto);
    List<LV> dtoToLv(List<DTO> dto);
    AV dtoToAv(DTO dto);
    List<AV> dtoToAv(List<DTO> dto);
    EV dtoToEv(DTO dto);
    List<EV> dtoToEv(List<DTO> dto);

    default Page<LV> dtoToLv(Page<DTO> dto){
        Page<LV> pl = null;
        if(dto!=null){
            List<LV> pageList = dtoToLv(dto.getList());
            pl = new Page<LV>(dto, pageList);
        }
        return pl;
    }

}
