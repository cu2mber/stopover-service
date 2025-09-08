package com.cu2mber.stopoverservice.repository;

public interface CustomStopoverRepository {

    boolean existsByLocalAndStopover(int localNo, String stopoverName);
}
