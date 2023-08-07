package com.hero.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.hero.dto.entity.Organisation;

public class OrganisationMapper implements RowMapper<Organisation>{

	@Override
	public Organisation mapRow(ResultSet result, int rowNum) throws SQLException {
		
		Organisation organisation = new Organisation();
		
		organisation.setOrganisationId(result.getInt("organisationId"));
		organisation.setOrganisationName(result.getString("organisationName"));
		organisation.setOrganisationDescription(result.getString("organisationDescription"));
		organisation.setOrganisationAddress(result.getString("organisationAddress"));
		organisation.setOrganisationContactInfo(result.getString("organisationContactInfo"));
		
		return organisation;
	}

}
