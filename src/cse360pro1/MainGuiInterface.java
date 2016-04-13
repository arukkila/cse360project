/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cse360pro1;

/**
 *
 * @author Michael
 */
public interface MainGuiInterface {
    public void setMainModel(MainModelInterface model);
    public void setStatsModel(StatsModelInterface statsModel);
    public void notifyModelChanged();
}
