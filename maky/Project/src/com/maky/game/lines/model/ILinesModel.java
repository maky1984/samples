package com.maky.game.lines.model;

import com.maky.app.model.IModel;

public interface ILinesModel extends IModel {

	public void setLevel(int number);

	public void selectCell(int column, int raw);

	public void moveBall(int fromCol, int fromRaw, int toCol, int toRaw);

}
