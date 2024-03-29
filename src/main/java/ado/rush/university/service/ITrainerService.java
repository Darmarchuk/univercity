package ado.rush.university.service;

import ado.rush.university.entity.Trainer;

public interface ITrainerService {
    Trainer findTrinerByLogin (String login);

}
