/*
 * Copyright (C) 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.telephony.satellite.wrapper;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * SatelliteAccessConfigurationWrapper2 is used to store satellite access configuration
 * that will be applied to the satellite communication at the corresponding region.
 *
 * @hide
 */
public class SatelliteAccessConfigurationWrapper2 implements Parcelable {
    /**
     * The list of satellites available at the current location.
     */
    @NonNull
    private List<SatelliteInfoWrapper> mSatelliteInfoList;

    /**
     * The list of tag IDs associated with the current location
     */
    @NonNull
    private List<Integer> mTagIdList;

    /**
     * The list of carrier IDs associated with the current location
     */
    @NonNull
    private List<Integer> mCarrierIdList;

    /**
     * Constructor for {@link SatelliteAccessConfigurationWrapper2}.
     *
     * @param satelliteInfos The list of {@link SatelliteInfoWrapper} objects representing
     *                       the satellites accessible with this configuration.
     * @param tagidList      The list of tag IDs associated with this configuration.
     */
    public SatelliteAccessConfigurationWrapper2(@NonNull List<SatelliteInfoWrapper> satelliteInfos,
            @NonNull List<Integer> tagidList) {
        this(satelliteInfos, tagidList, new ArrayList<>());
    }

    /**
     * Constructor for {@link SatelliteAccessConfigurationWrapper2}.
     *
     * @param satelliteInfos The list of {@link SatelliteInfoWrapper} objects representing
     *                       the satellites accessible with this configuration.
     * @param tagidList      The list of tag IDs associated with this configuration.
     * @param carrierIdList  The list of carrier Ids associated with this configuration.
     */
    public SatelliteAccessConfigurationWrapper2(@NonNull List<SatelliteInfoWrapper> satelliteInfos,
            @NonNull List<Integer> tagidList, @NonNull List<Integer> carrierIdList) {
        mSatelliteInfoList = satelliteInfos;
        mTagIdList = tagidList;
        mCarrierIdList = carrierIdList;
    }

    public SatelliteAccessConfigurationWrapper2(Parcel in) {
        mSatelliteInfoList = in.createTypedArrayList(SatelliteInfoWrapper.CREATOR);
        mTagIdList = new ArrayList<>();
        in.readList(mTagIdList, Integer.class.getClassLoader(), Integer.class);
        mCarrierIdList = new ArrayList<>();
        in.readList(mCarrierIdList, Integer.class.getClassLoader(), Integer.class);
    }

    public static final Parcelable.Creator<SatelliteAccessConfigurationWrapper2> CREATOR =
            new Parcelable.Creator<SatelliteAccessConfigurationWrapper2>() {
                @Override
                public SatelliteAccessConfigurationWrapper2 createFromParcel(Parcel in) {
                    return new SatelliteAccessConfigurationWrapper2(in);
                }

                @Override
                public SatelliteAccessConfigurationWrapper2[] newArray(int size) {
                    return new SatelliteAccessConfigurationWrapper2[size];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeTypedList(mSatelliteInfoList);
        dest.writeList(mTagIdList);
        dest.writeList(mCarrierIdList);
    }

    /**
     * Returns a list of {@link SatelliteInfoWrapper} objects representing the satellites
     * associated with this object.
     *
     * @return The list of {@link SatelliteInfoWrapper} objects.
     */
    @NonNull
    public List<SatelliteInfoWrapper> getSatelliteInfos() {
        return mSatelliteInfoList;
    }

    /**
     * Returns a list of tag IDs associated with this object.
     *
     * @return The list of tag IDs.
     */
    @NonNull
    public List<Integer> getTagIds() {
        return mTagIdList;
    }

    /**
     * Returns a list of carrier IDs associated with this object.
     *
     * @return The list of carrier IDs.
     */
    @NonNull
    public List<Integer> getCarrierIds() {
        return mCarrierIdList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SatelliteAccessConfigurationWrapper2 that)) return false;

        return mSatelliteInfoList.equals(that.mSatelliteInfoList)
                && Objects.equals(mTagIdList, that.mTagIdList)
                && Objects.equals(mCarrierIdList, that.mCarrierIdList);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(mSatelliteInfoList);
        result = 31 * result + Objects.hashCode(mTagIdList) + Objects.hashCode(mCarrierIdList);
        return result;
    }

    @Override
    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SatelliteAccessConfigurationWrapper2{");
        sb.append("mSatelliteInfoList=").append(mSatelliteInfoList);
        sb.append(", mTagIds=").append(mTagIdList);
        sb.append(", mCarrierIds=").append(mCarrierIdList);
        sb.append('}');
        return sb.toString();
    }
}
