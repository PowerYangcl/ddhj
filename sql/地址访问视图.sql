DROP VIEW
IF EXISTS v_area;

CREATE VIEW v_area AS SELECT DISTINCT
	`a`.`code` AS `area_code`,
	ifnull(`a`.`name`, '') AS `name`
FROM
	`t_area` `a`
WHERE
	a.parent_code = '0'
UNION
	SELECT DISTINCT
		`b`.`code` AS `area_code`,
		ifnull(
			concat(`a`.`name`, `b`.`name`),
			''
		) AS `name`
	FROM
		(
			(
				`t_area` `a`
				LEFT JOIN `t_area` `b` ON `b`.parent_code = a.`code`
			)
		)
	UNION
		SELECT DISTINCT
			`c`.`code` AS `area_code`,
			ifnull(
				concat(
					`a`.`name`,
					`b`.`name`,
					`c`.`name`
				),
				''
			) AS `name`
		FROM
			(
				(
					`t_area` `a`
					LEFT JOIN `t_area` `b` ON `b`.parent_code = a.`code`
				)
				LEFT JOIN `t_area` `c` ON `c`.`parent_code` = b.`code`
			);

