package dev.patika.veterinaryManagement.core.config.modelMapper;

import org.modelmapper.ModelMapper;
public interface IModelMapperService {

    ModelMapper forRequest();
    ModelMapper forResponse();
}
