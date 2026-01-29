package com.gps.gateway.resource;

import com.gps.gateway.client.VehicleClient;
import com.gps.gateway.client.TrackingClient;
import com.gps.gateway.dto.VehicleStatusDTO;
import com.gps.gateway.dto.VehicleDTO;
import com.gps.gateway.dto.TrackingDTO;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/vehicle-status")
public class VehicleStatusResource {

    @Inject
    @RestClient
    VehicleClient vehicleClient;

    @Inject
    @RestClient
    TrackingClient trackingClient;

    @GET
    public VehicleStatusDTO getVehicleStatus(@QueryParam("vehicleId") Long vehicleId) {

        VehicleDTO vehicle = vehicleClient.getAll()
                .stream()
                .filter(v -> v.id.equals(vehicleId))
                .findFirst()
                .orElse(null);

        TrackingDTO lastPos = trackingClient.getLastByVehicle(vehicleId);

        VehicleStatusDTO response = new VehicleStatusDTO();
        response.vehicle = vehicle;
        response.lastPosition = lastPos;

        return response;
    }
}
