package com.IndustrialDataAPI.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Pieces")
public class Pieces {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pieces_id;

    @Column(name = "number_pieces")
    private int numberPieces;

    @Column(name = "bad_pieces")
    private int badPieces;

    @Column(name = "running_time")
    private Long runningTime;

    @Column(name = "stopped_time")
    private Long stoppedTime;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "shift")
    private String shift;

    @ManyToOne
    @JoinColumn(name = "machine_id")
    private Machines machines;

    public Pieces(Long pieces_id, int numberPieces, int badPieces, Long runningTime, Long stoppedTime, LocalDate date, String shift, Machines machines) {
        this.pieces_id = pieces_id;
        this.numberPieces = numberPieces;
        this.badPieces = badPieces;
        this.runningTime = runningTime;
        this.stoppedTime = stoppedTime;
        this.date = date;
        this.shift = shift;
        this.machines = machines;
    }

    public Long getPieces_id() {
        return pieces_id;
    }

    public void setPieces_id(Long pieces_id) {
        this.pieces_id = pieces_id;
    }

    public int getNumberPieces() {
        return numberPieces;
    }

    public void setNumberPieces(int numberPieces) {
        this.numberPieces = numberPieces;
    }

    public int getBadPieces() {
        return badPieces;
    }

    public void setBadPieces(int badPieces) {
        this.badPieces = badPieces;
    }

    public Long getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(Long runningTime) {
        this.runningTime = runningTime;
    }

    public Long getStoppedTime() {
        return stoppedTime;
    }

    public void setStoppedTime(Long stoppedTime) {
        this.stoppedTime = stoppedTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public Machines getMachines() {
        return machines;
    }

    public void setMachines(Machines machines) {
        this.machines = machines;
    }
}
