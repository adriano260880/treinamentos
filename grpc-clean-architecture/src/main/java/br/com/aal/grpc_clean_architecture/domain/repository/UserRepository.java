package br.com.aal.grpc_clean_architecture.domain.repository;

import br.com.aal.grpc_clean_architecture.domain.model.User;

public interface UserRepository {

    User findById(Long id);
}
