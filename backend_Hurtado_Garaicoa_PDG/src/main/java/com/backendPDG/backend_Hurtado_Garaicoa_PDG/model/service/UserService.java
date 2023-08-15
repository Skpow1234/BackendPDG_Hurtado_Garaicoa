package com.backendPDG.backend_Hurtado_Garaicoa_PDG.model.service;

import com.backendPDG.backend_Hurtado_Garaicoa_PDG.security.request.AuthRequest;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.security.request.RegisterRequest;
import com.backendPDG.backend_Hurtado_Garaicoa_PDG.security.response.AuthResponse;

public interface UserService {
    AuthResponse register(RegisterRequest request);
    AuthResponse authenticate(AuthRequest request);
}
