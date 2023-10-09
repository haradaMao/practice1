-- idはAUTO_INCREMENTによって自動生成されるので書かなくてOK
-- varcharはchar型のため’’でくくる
insert into issues(summary, description) values ('バグA', 'バグがあります');
insert into issues(summary, description) values ('機能要望B', 'Bに追加機能が欲しいです');
insert into issues(summary, description) values ('画面Cが遅い', '早くしてほしいです');

